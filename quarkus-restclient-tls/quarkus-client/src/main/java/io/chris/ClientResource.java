package io.chris;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Path("/client")
public class ClientResource {

    @RestClient
    @Inject
    Client client;

    @ConfigProperty(name = "url")
    URL urlServer;
    @ConfigProperty(name = "keyStore")
    String keyStoreFile;
    @ConfigProperty(name = "keyStorePassword")
    String keyStoreFilePassword;
    @ConfigProperty(name = "trustStore")
    String trustStoreFile;
    @ConfigProperty(name = "trustStorePassword")
    String trustStoreFilePassword;

    @GET
    public String get() {
        return client.call();
    }

    @GET
    @Path("clientBuild")
    @Produces(MediaType.TEXT_PLAIN)
    public String clientBuilder() throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException {
        KeyStore keyStore = loadKeyStore(keyStoreFile, keyStoreFilePassword);
        KeyStore trustStore = loadKeyStore(trustStoreFile, trustStoreFilePassword);

        Client clientBuild = RestClientBuilder.newBuilder()
                .baseUrl(urlServer)
                .keyStore(keyStore, keyStoreFilePassword)
                .trustStore(trustStore)
                .build(Client.class);
        return clientBuild.call();
    }

    private KeyStore loadKeyStore(String storeFile, String storePassword) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(storeFile)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("KeyStore file not found: " + storeFile);
            }
            keyStore.load(inputStream, storePassword.toCharArray());
        }

        return keyStore;
    }
}

package org.acme;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Singleton;
import javax.ws.rs.Produces;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Singleton
public class AzureProducer {

    @ConfigProperty(name = "storage.connection")
    String storageConnectionString;

    @ConfigProperty(name = "storage.container-name")
    String containerName;

    CloudBlobContainer container;

    @Produces
    public CloudBlobContainer produce() throws URISyntaxException, InvalidKeyException, StorageException {
        if (container == null) {
            // Create a BlobServiceClient object which will be used to create a container client
            container = CloudStorageAccount.parse(storageConnectionString)
                    .createCloudBlobClient()
                    .getContainerReference(containerName);
            container.createIfNotExists();
        }
        return container;
    }
}

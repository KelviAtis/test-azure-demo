package org.acme;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Singleton
public class AzureService {

//    @Inject
    CloudBlobContainer container = null;

    public ByteArrayOutputStream downloadObject(String name, String uuid)
            throws URISyntaxException, StorageException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        container.getDirectoryReference(name).getAppendBlobReference(uuid).download(baos);
        return baos;
    }

    public CloudBlockBlob uploadObject(FormData formData, String uuid)
            throws URISyntaxException, StorageException, IOException {

//        String storageConnectionString = "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;QueueEndpoint=http://127.0.0.1:10001/devstoreaccount1;";
        String storageConnectionString = "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://blobstorage.n-ess.it/devstoreaccount1;QueueEndpoint=http://blobstorage.n-ess.it/devstoreaccount1;";
        String containerName = "wastetracing";

        if (container == null) {
            // Create a BlobServiceClient object which will be used to create a container client
            try {
                container = CloudStorageAccount.parse(storageConnectionString)
                        .createCloudBlobClient()
                        .getContainerReference(containerName);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            container.createIfNotExists();
        }

        CloudBlockBlob blockBlob = container.getDirectoryReference(containerName)
                .getBlockBlobReference(formData.fileName);
        blockBlob.upload(formData.data, -1);
        return blockBlob;
    }
}

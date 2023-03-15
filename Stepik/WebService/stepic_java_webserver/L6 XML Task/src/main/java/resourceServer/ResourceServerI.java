package resourceServer;

import resources.TestResource;

public interface ResourceServerI {
    String getName();

    int getAge();

    public void setTestResource(TestResource testResource);
}

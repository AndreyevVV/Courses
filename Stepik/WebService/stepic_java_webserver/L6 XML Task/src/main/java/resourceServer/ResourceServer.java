package resourceServer;

import resources.TestResource;

public class ResourceServer implements ResourceServerI{
    private TestResource testResource;

    public ResourceServer() {

    }

    public ResourceServer(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public String getName() {
        return testResource.getName();
    }

    @Override
    public int getAge() {
        return testResource.getAge();
    }

    @Override
    public void setTestResource(TestResource testResource) {
        this.testResource = testResource;
    }
}

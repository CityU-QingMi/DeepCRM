    public void testLoadOrder() throws Exception {
        configuration = new MockConfiguration();
        ((MockConfiguration) configuration).selfRegister();
        container = configuration.getContainer();

        XmlConfigurationProvider prov = new XmlConfigurationProvider("xwork-test-load-order.xml", true) {
            @Override
            protected Iterator<URL> getConfigurationUrls(String fileName) throws IOException {
                List<URL> urls = new ArrayList<>();
                urls.add(ClassLoaderUtil.getResource("com/opensymphony/xwork2/config/providers/loadorder1/xwork-test-load-order.xml", XmlConfigurationProvider.class));
                urls.add(ClassLoaderUtil.getResource("com/opensymphony/xwork2/config/providers/loadorder2/xwork-test-load-order.xml", XmlConfigurationProvider.class));
                urls.add(ClassLoaderUtil.getResource("com/opensymphony/xwork2/config/providers/loadorder3/xwork-test-load-order.xml", XmlConfigurationProvider.class));
                return urls.iterator();
            }
        };
        prov.setObjectFactory(container.getInstance(ObjectFactory.class));
        prov.setFileManagerFactory(container.getInstance(FileManagerFactory.class));
        prov.init(configuration);
        List<Document> docs = prov.getDocuments();
        assertEquals(3, docs.size());

        assertEquals(1, XmlHelper.getLoadOrder(docs.get(0)).intValue());
        assertEquals(2, XmlHelper.getLoadOrder(docs.get(1)).intValue());
        assertEquals(3, XmlHelper.getLoadOrder(docs.get(2)).intValue());
    }

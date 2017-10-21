    @Override
    public void init(URL url, XmlParser.Node config, XmlConfiguration configuration)
    {
        try
        {
            _configuration = configuration;

            Resource resource = url != null
                    ? new UrlResource(url)
                    : new ByteArrayResource(("" +
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\">" +
                    config).getBytes(StandardCharsets.UTF_8));

            _beanFactory = new DefaultListableBeanFactory()
            {
                @Override
                protected void applyPropertyValues(String beanName, BeanDefinition mbd, BeanWrapper bw, PropertyValues pvs)
                {
                    _configuration.initializeDefaults(bw.getWrappedInstance());
                    super.applyPropertyValues(beanName, mbd, bw, pvs);
                }
            };

            new XmlBeanDefinitionReader(_beanFactory).loadBeanDefinitions(resource);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

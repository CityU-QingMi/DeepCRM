public void testGetThemePropsThroughFileSystem() throws Exception {

        URL dummyResourceUrl = getClass().getResource("dummy.properties");
        File dummyResourceFile = new File(dummyResourceUrl.getFile().replaceAll("%20", " "));
        String themePropertiesDir = dummyResourceFile.getParent();

        System.out.println("dummy resource url="+dummyResourceUrl);
        System.out.println("resource file="+dummyResourceFile);
        System.out.println("theme properties dir="+themePropertiesDir);

        assertTrue(dummyResourceFile.exists());
        assertNotNull(themePropertiesDir);

        Template template = new Template(themePropertiesDir, "theme1", "template1");

        TemplateEngine templateEngine = new InnerBaseTemplateEngine("themeThroughFileSystem.properties");
        Map propertiesMap = templateEngine.getThemeProps(template);

        assertNotNull(propertiesMap);
        assertTrue(propertiesMap.size() > 0);

    }

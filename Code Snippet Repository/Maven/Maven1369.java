    public DefaultModelBuilder newInstance()
    {
        DefaultModelBuilder modelBuilder = new DefaultModelBuilder();

        modelBuilder.setModelProcessor( newModelProcessor() );
        modelBuilder.setModelValidator( newModelValidator() );
        modelBuilder.setModelNormalizer( newModelNormalizer() );
        modelBuilder.setModelPathTranslator( newModelPathTranslator() );
        modelBuilder.setModelUrlNormalizer( newModelUrlNormalizer() );
        modelBuilder.setModelInterpolator( newModelInterpolator() );
        modelBuilder.setInheritanceAssembler( newInheritanceAssembler() );
        modelBuilder.setProfileInjector( newProfileInjector() );
        modelBuilder.setProfileSelector( newProfileSelector() );
        modelBuilder.setSuperPomProvider( newSuperPomProvider() );
        modelBuilder.setDependencyManagementImporter( newDependencyManagementImporter() );
        modelBuilder.setDependencyManagementInjector( newDependencyManagementInjector() );
        modelBuilder.setLifecycleBindingsInjector( newLifecycleBindingsInjector() );
        modelBuilder.setPluginManagementInjector( newPluginManagementInjector() );
        modelBuilder.setPluginConfigurationExpander( newPluginConfigurationExpander() );
        modelBuilder.setReportConfigurationExpander( newReportConfigurationExpander() );
        modelBuilder.setReportingConverter( newReportingConverter() );

        return modelBuilder;
    }

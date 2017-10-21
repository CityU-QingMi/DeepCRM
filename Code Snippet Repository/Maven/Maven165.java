    private static DeploymentRepository copyDistributionRepository( DeploymentRepository parentRepository )
    {
        DeploymentRepository repository = new DeploymentRepository();

        repository.setId( parentRepository.getId() );

        repository.setName( parentRepository.getName() );

        repository.setUrl( parentRepository.getUrl() );

        repository.setLayout( parentRepository.getLayout() );

        repository.setUniqueVersion( parentRepository.isUniqueVersion() );

        return repository;
    }

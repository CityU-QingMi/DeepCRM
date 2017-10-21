		@Autowired
		public RepositoryConstructorInjectionBean(Repository<String> stringRepository, Repository<Integer> integerRepository,
				Repository<String>[] stringRepositoryArray, Repository<Integer>[] integerRepositoryArray,
				List<Repository<String>> stringRepositoryList, List<Repository<Integer>> integerRepositoryList,
				Map<String, Repository<String>> stringRepositoryMap, Map<String, Repository<Integer>> integerRepositoryMap) {
			this.stringRepository = stringRepository;
			this.integerRepository = integerRepository;
			this.stringRepositoryArray = stringRepositoryArray;
			this.integerRepositoryArray = integerRepositoryArray;
			this.stringRepositoryList = stringRepositoryList;
			this.integerRepositoryList = integerRepositoryList;
			this.stringRepositoryMap = stringRepositoryMap;
			this.integerRepositoryMap = integerRepositoryMap;
		}

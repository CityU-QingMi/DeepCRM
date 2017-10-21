	public UITagExample() {
		favouriteLanguages.add(new Language("EnglishKey", "English Language", "color: blue; font-style: italic;"));
		favouriteLanguages.add(new Language("FrenchKey", "French Language", "color: grey;"));
		favouriteLanguages.add(new Language("SpanishKey", "Spanish Language", "color: red; font-wight: bold;"));

		VehicalType car = new VehicalType("CarKey", "Car");
		VehicalType motorcycle = new VehicalType("MotorcycleKey", "Motorcycle");
		vehicalTypeList.add(car);
		vehicalTypeList.add(motorcycle);

		List cars = new ArrayList();
		cars.add(new VehicalSpecific("MercedesKey", "Mercedes"));
		cars.add(new VehicalSpecific("HondaKey", "Honda"));
		cars.add(new VehicalSpecific("FordKey", "Ford"));

		List motorcycles = new ArrayList();
		motorcycles.add(new VehicalSpecific("SuzukiKey", "Suzuki"));
		motorcycles.add(new VehicalSpecific("YamahaKey", "Yamaha"));

		vehicalSpecificMap.put(car, cars);
		vehicalSpecificMap.put(motorcycle, motorcycles);
	}

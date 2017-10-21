	public PersonTuple(boolean helloWorld, Person personA, Person personB, Constant constant) {
		this.helloWorld = helloWorld;
		this.personA = personA;
		this.personB = personB;
		this.constant = constant;

		this.personTupleId.personAId = personA.getId();
		this.personTupleId.personBId = personB.getId();
		this.personTupleId.constantId = constant.getId();

		personA.getPersonATuples().add( this );
		personB.getPersonBTuples().add( this );
	}

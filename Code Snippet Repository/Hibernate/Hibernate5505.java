	@Override
    public String toString()
	{
	    final String TAB = "    ";

	    String retValue = "";

	    retValue = "Cat ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "name = " + this.name + TAB
	        + "dateOfBirth = " + this.dateOfBirth + TAB
	        + "age = " + this.age + TAB
	        + "length = " + this.length + TAB
	        + "lastUpdate = " + this.lastUpdate + TAB
	        + "manualVersion = " + this.manualVersion + TAB
	        + "postVersion = " + Cat.postVersion + TAB
	        + "kittens = " + this.kittens + TAB
	        + " )";

	    return retValue;
	}

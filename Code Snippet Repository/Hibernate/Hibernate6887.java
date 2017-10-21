	    public JoinCounter(int val) {
	        super();
	        this.expectedNumberOfJoins = val;
	        try {
	        	nextValRegex = ".*" + getDialect().getSelectSequenceNextValString(".*") + ".*";
	        	nextValRegex = nextValRegex.replace( "(", "\\(" );
	        	nextValRegex = nextValRegex.replace( ")", "\\)" );
	        } catch (MappingException ex) {
	        	nextValRegex = "nextval";
	        }
	    }

	public boolean containsEL() {
	    Iterator<ELNode> iter = list.iterator();
	    while (iter.hasNext()) {
	        ELNode n = iter.next();
	        if (n instanceof Root) {
	            return true;
	        }
	    }
	    return false;
	}

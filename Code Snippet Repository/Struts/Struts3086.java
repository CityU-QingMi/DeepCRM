    public void release() {        
        Enumeration enumeration = perThreadDataVector.elements();
        while (enumeration.hasMoreElements()) {
	    PerThreadData ptd = (PerThreadData)enumeration.nextElement();
            if (ptd.handlers != null) {
                for (int i=ptd.current; i>=0; i--) {
                    if (ptd.handlers[i] != null) {
                        ptd.handlers[i].release();
		    }
                }
            }
        }
    }

    public synchronized String getString() {
	// check state and initialize buffer
	if (outputFileName == null)
	    throw new IllegalStateException();
        StringBuffer out = new StringBuffer();

	// start the SMAP
	out.append("SMAP\n");
	out.append(outputFileName + '\n');
	out.append(defaultStratum + '\n');

	// include embedded SMAPs
	if (doEmbedded) {
	    int nEmbedded = embedded.size();
	    for (int i = 0; i < nEmbedded; i++) {
	        out.append(embedded.get(i));
	    }
	}

	// print our StratumSections, FileSections, and LineSections
	int nStrata = strata.size();
	for (int i = 0; i < nStrata; i++) {
	    SmapStratum s = (SmapStratum) strata.get(i);
	    out.append(s.getString());
	}

	// end the SMAP
	out.append("*E\n");

	return out.toString();
    }

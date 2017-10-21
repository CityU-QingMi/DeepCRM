	private String escapeCDATA(String text) {
            if( text==null ) return "";
	    int len = text.length();
	    CharArrayWriter result = new CharArrayWriter(len);
	    for (int i=0; i<len; i++) {
		if (((i+2) < len)
		        && (text.charAt(i) == ']')
		        && (text.charAt(i+1) == ']')
		        && (text.charAt(i+2) == '>')) {
		    // match found
		    result.write(']');
		    result.write(']');
		    result.write('&');
		    result.write('g');
		    result.write('t');
		    result.write(';');
		    i += 2;
		} else {
		    result.write(text.charAt(i));
		}
	    }
	    return result.toString();
	}

	public void release() {
		out = baseOut;
		try {
			if (isIncluded) {
				((JspWriterImpl) out).flushBuffer();
				// push it into the including jspWriter
			} else {
				// Old code:
				// out.flush();
				// Do not flush the buffer even if we're not included (i.e.
				// we are the main page. The servlet will flush it and close
				// the stream.
				((JspWriterImpl) out).flushBuffer();
			}
		} catch (IOException ex) {
            IllegalStateException ise = new IllegalStateException(Localizer.getMessage("jsp.error.flush"), ex);
            throw ise;
		} finally {
		    servlet = null;
		    config = null;
		    context = null;
		    applicationContext = null;
		    elContext = null;
		    errorPageURL = null;
		    request = null;
		    response = null;
		    depth = -1;
		    baseOut.recycle();
		    session = null;
		    attributes.clear();
        }
	}

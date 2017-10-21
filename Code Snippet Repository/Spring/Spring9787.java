	@Override
	public String getOriginalFilename() {
		String filename = this.fileItem.getName();
		if (filename == null) {
			// Should never happen.
			return "";
		}
		if (this.preserveFilename) {
			// Do not try to strip off a path...
			return filename;
		}

		// Check for Unix-style path
		int unixSep = filename.lastIndexOf("/");
		// Check for Windows-style path
		int winSep = filename.lastIndexOf("\\");
		// Cut off at latest possible point
		int pos = (winSep > unixSep ? winSep : unixSep);
		if (pos != -1)  {
			// Any sort of path separator found...
			return filename.substring(pos + 1);
		}
		else {
			// A plain name
			return filename;
		}
	}

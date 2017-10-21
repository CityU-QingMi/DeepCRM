	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		if (!isAvailable()) {
			throw new IllegalStateException("File has already been moved - cannot be transferred again");
		}

		if (dest.exists() && !dest.delete()) {
			throw new IOException(
					"Destination file [" + dest.getAbsolutePath() + "] already exists and could not be deleted");
		}

		try {
			this.fileItem.write(dest);
			if (logger.isDebugEnabled()) {
				String action = "transferred";
				if (!this.fileItem.isInMemory()) {
					action = (isAvailable() ? "copied" : "moved");
				}
				logger.debug("Multipart file '" + getName() + "' with original filename [" +
						getOriginalFilename() + "], stored " + getStorageDescription() + ": " +
						action + " to [" + dest.getAbsolutePath() + "]");
			}
		}
		catch (FileUploadException ex) {
			throw new IllegalStateException(ex.getMessage(), ex);
		}
		catch (IllegalStateException ex) {
			// Pass through when coming from FileItem directly
			throw ex;
		}
		catch (IOException ex) {
			// From I/O operations within FileItem.write
			throw ex;
		}
		catch (Exception ex) {
			throw new IOException("File transfer failed", ex);
		}
	}

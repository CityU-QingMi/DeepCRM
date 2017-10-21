    private void deleteFiles(ServletRequest request)
    {
        if (!_deleteFiles)
            return;
        
        MultiPartInputStreamParser mpis = (MultiPartInputStreamParser)request.getAttribute(MULTIPART);
        if (mpis != null)
        {
            try
            {
                mpis.deleteParts();
            }
            catch (Exception e)
            {
                _context.log("Error deleting multipart tmp files", e);
            }
        }
        request.removeAttribute(MULTIPART);
    }

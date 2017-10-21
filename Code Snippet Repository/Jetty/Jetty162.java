        public void filesChanged(List<String> changedFileNames)
        {
            boolean isScanned = false;
            try
            {
                Iterator<String> itor = changedFileNames.iterator();
                while (!isScanned && itor.hasNext())
                {
                    isScanned = awc.isScanned(Resource.newResource(itor.next()).getFile());
                }
                if (isScanned)
                {
                    awc.stop();
                    awc.start();
                }
            }
            catch (Exception e)
            {
                TaskLog.log(e.getMessage());
            }
        }

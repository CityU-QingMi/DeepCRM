    void removeOldFiles(ZonedDateTime now)
    {
        if (_retainDays>0)
        {
            // Establish expiration time, based on configured TimeZone
            long expired = now.minus(_retainDays, ChronoUnit.DAYS).toInstant().toEpochMilli();
            
            File file= new File(_filename);
            File dir = new File(file.getParent());
            String fn=file.getName();
            int s=fn.toLowerCase(Locale.ENGLISH).indexOf(YYYY_MM_DD);
            if (s<0)
                return;
            String prefix=fn.substring(0,s);
            String suffix=fn.substring(s+YYYY_MM_DD.length());

            String[] logList=dir.list();
            for (int i=0;i<logList.length;i++)
            {
                fn = logList[i];
                if(fn.startsWith(prefix)&&fn.indexOf(suffix,prefix.length())>=0)
                {        
                    File f = new File(dir,fn);
                    if(f.lastModified() < expired)
                    {
                        f.delete();
                    }
                }
            }
        }
    }

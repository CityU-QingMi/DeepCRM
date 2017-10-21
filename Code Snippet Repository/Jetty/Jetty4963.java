        public boolean isQuiet(long now, long quietTime)
        {
            long lastModified = modified;
            long lastLength = length;
            
            check();
            
            if (lastModified == modified && lastLength == length)
                return (now-checked)>=quietTime;
            
            checked = now;
            return false;
        }

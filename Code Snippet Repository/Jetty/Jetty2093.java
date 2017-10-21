        public void spin()
        {
            long result=-1;
            long end=System.currentTimeMillis()+DURATION+1000;
            while (!done && System.currentTimeMillis()<end)
            {
                for (int i=0;i<1000000000;i++)
                    result^=i;
            }
            
            if (result==42)
                System.err.println("Bingo!");
        }

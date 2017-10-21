        @Override
        public int compare(String c1, String c2)
        {
            boolean b1=isBlackListCipher(c1);
            boolean b2=isBlackListCipher(c2);
            if (b1==b2)
                return 0;
            if (b1)
                return 1;
            return -1;
        }

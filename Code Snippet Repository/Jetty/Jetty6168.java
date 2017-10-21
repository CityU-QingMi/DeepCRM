        @Override
        public Quotes decode(Reader reader) throws DecodeException, IOException
        {
            Quotes quotes = new Quotes();
            try (BufferedReader buf = new BufferedReader(reader))
            {
                String line;
                while ((line = buf.readLine()) != null)
                {
                    switch (line.charAt(0))
                    {
                        case 'a':
                            quotes.setAuthor(line.substring(2));
                            break;
                        case 'q':
                            quotes.addQuote(line.substring(2));
                            break;
                    }
                }
            }
            return quotes;
        }

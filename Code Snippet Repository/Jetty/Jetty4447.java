        @Override
        public Void call() throws Exception
        {
            for (int i = 0; i < NUM_LOOPS; i++)
            {
                HttpTester.Request request = HttpTester.newRequest();

                request.setMethod("GET");
                request.setHeader("host", "tester");
                request.setURI("/context/test?priority=" + (_num % QoSFilter.__DEFAULT_MAX_PRIORITY));
                request.setHeader("num", _num + "");

                String responseString = _connectors[_num].getResponse(BufferUtil.toString(request.generate()));

                assertThat("Response contains", responseString, containsString("HTTP"));
            }

            return null;
        }

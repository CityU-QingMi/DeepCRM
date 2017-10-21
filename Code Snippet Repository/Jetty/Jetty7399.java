    @Test
    public void testOne() throws Exception
    {
        System.err.printf("[%d] TEST   c=%s, m=%s, delayDispatch=%b delayInFrame=%s content-length:%d expect=%d read=%d content:%s%n",_id,_client.getSimpleName(),_mode,__config.isDelayDispatchUntilContent(),_delay,_length,_status,_read,_send);

        TestClient client=_client.newInstance();
        String response = client.send("/ctx/test?mode="+_mode,50,_delay,_length,_send);
        
        int sum=0;
        for (String s:_send)
            for (char c : s.toCharArray())
                sum+=c;
        
        assertThat(response,startsWith("HTTP"));
        assertThat(response,Matchers.containsString(" "+_status+" "));
        assertThat(response,Matchers.containsString("read="+_read));
        assertThat(response,Matchers.containsString("sum="+sum));
    }

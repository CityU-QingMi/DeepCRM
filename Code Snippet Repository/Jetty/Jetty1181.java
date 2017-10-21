    @Test
    public void testDecode()
    {
        testDecode(6,0,"00");
        testDecode(6,1,"01");
        testDecode(6,62,"3e");
        testDecode(6,63,"3f00");
        testDecode(6,63+1,"3f01");
        testDecode(6,63+0x7e,"3f7e");
        testDecode(6,63+0x7f,"3f7f");
        testDecode(6,63+0x80,"3f8001");
        testDecode(6,63+0x81,"3f8101");
        testDecode(6,63+0x7f+0x80*0x01,"3fFf01");
        testDecode(6,63+0x00+0x80*0x02,"3f8002");
        testDecode(6,63+0x01+0x80*0x02,"3f8102");
        testDecode(6,63+0x7f+0x80*0x7f,"3fFf7f");
        testDecode(6,63+0x00+0x80*0x80,     "3f808001");
        testDecode(6,63+0x7f+0x80*0x80*0x7f,"3fFf807f");
        testDecode(6,63+0x00+0x80*0x80*0x80,"3f80808001");
        
        testDecode(8,0,"00");
        testDecode(8,1,"01");
        testDecode(8,128,"80");
        testDecode(8,254,"Fe");
        testDecode(8,255,"Ff00");
        testDecode(8,255+1,"Ff01");
        testDecode(8,255+0x7e,"Ff7e");
        testDecode(8,255+0x7f,"Ff7f");
        testDecode(8,255+0x80,"Ff8001");
        testDecode(8,255+0x00+0x80*0x80,"Ff808001");
    }

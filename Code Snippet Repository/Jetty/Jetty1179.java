    @Test
    public void testEncode()
    {
        testEncode(6,0,"00");
        testEncode(6,1,"01");
        testEncode(6,62,"3e");
        testEncode(6,63,"3f00");
        testEncode(6,63+1,"3f01");
        testEncode(6,63+0x7e,"3f7e");
        testEncode(6,63+0x7f,"3f7f");
        testEncode(6,63+0x00+0x80*0x01,"3f8001");
        testEncode(6,63+0x01+0x80*0x01,"3f8101");
        testEncode(6,63+0x7f+0x80*0x01,"3fFf01");
        testEncode(6,63+0x00+0x80*0x02,"3f8002");
        testEncode(6,63+0x01+0x80*0x02,"3f8102");
        testEncode(6,63+0x7f+0x80*0x7f,"3fFf7f");
        testEncode(6,63+0x00+0x80*0x80,     "3f808001");
        testEncode(6,63+0x7f+0x80*0x80*0x7f,"3fFf807f");
        testEncode(6,63+0x00+0x80*0x80*0x80,"3f80808001");

        testEncode(8,0,"00");
        testEncode(8,1,"01");
        testEncode(8,128,"80");
        testEncode(8,254,"Fe");
        testEncode(8,255,"Ff00");
        testEncode(8,255+1,"Ff01");
        testEncode(8,255+0x7e,"Ff7e");
        testEncode(8,255+0x7f,"Ff7f");
        testEncode(8,255+0x80,"Ff8001");
        testEncode(8,255+0x00+0x80*0x80,"Ff808001");
    }

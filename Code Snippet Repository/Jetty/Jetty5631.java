    @BeforeClass
    public static void setUp()
    throws Exception
    {
        privateAField = ServletA.class.getDeclaredField("privateA");
        protectedAField = ServletA.class.getDeclaredField("protectedA");
        publicAField = ServletA.class.getDeclaredField("publicA");
        defaultAField = ServletA.class.getDeclaredField("defaultA");
        privateBField = ServletB.class.getDeclaredField("privateB");
        protectedBField = ServletB.class.getDeclaredField("protectedB");
        publicBField = ServletB.class.getDeclaredField("publicB");
        defaultBField = ServletB.class.getDeclaredField("defaultB");
        privateCMethod = ServletC.class.getDeclaredMethod("setPrivateC", __INTEGER_ARG);
        protectedCMethod = ServletC.class.getDeclaredMethod("setProtectedC", __INTEGER_ARG);
        publicCMethod = ServletC.class.getDeclaredMethod("setPublicC", __INTEGER_ARG);
        defaultCMethod = ServletC.class.getDeclaredMethod("setDefaultC", __INTEGER_ARG);
        privateDMethod = ServletD.class.getDeclaredMethod("setPrivateD", __INTEGER_ARG);
        protectedDMethod = ServletD.class.getDeclaredMethod("setProtectedD", __INTEGER_ARG);
        publicDMethod = ServletD.class.getDeclaredMethod("setPublicD", __INTEGER_ARG);
        defaultDMethod = ServletD.class.getDeclaredMethod("setDefaultD", __INTEGER_ARG);
    }

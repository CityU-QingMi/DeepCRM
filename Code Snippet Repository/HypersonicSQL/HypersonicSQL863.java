    public double random(long seed) {

        if (this.seed != seed) {
            randomGenerator.setSeed(seed);

            this.seed = seed;
        }

        return randomGenerator.nextDouble();
    }

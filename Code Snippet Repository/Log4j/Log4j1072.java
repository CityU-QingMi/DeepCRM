        @Override
        public BurstFilter build() {
            if (this.rate <= 0) {
                this.rate = DEFAULT_RATE;
            }
            if (this.maxBurst <= 0) {
                this.maxBurst = (long) (this.rate * DEFAULT_RATE_MULTIPLE);
            }
            return new BurstFilter(this.level, this.rate, this.maxBurst, this.onMatch, this.onMismatch);
        }

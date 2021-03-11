package fr.discobee;

public class Specifications {

	private final String name;
	private final String token;
	private final int shards;

	private Specifications(String nameIn, String tokenIn, int shardsIn) {
		this.name = nameIn;
		this.token = tokenIn;
		this.shards = shardsIn;
	}

	public String getToken() {
		return this.token;
	}

	public int getShards() {
		return this.shards;
	}

	public String getName() {
		return this.name;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String name = "DiscoBee not";
		private String token = "$";
		private int shards = 0;

		public Builder token(String tokenIn) {
			this.token = tokenIn;
			return this;
		}

		public Builder shards(int shardsIn) {
			this.shards = shardsIn;
			return this;
		}

		public Builder name(String nameIn) {
			this.name = nameIn;
			return this;
		}

		public Specifications build() {
			return new Specifications(this.name, this.token, this.shards);
		}
	}

}

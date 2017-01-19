package models;

public class Word {
	private static int generatorId = 0;
	private int id;
	private String orth;
	private String base;
	private String tags;

	public Word(String orth, String base, String tags) {
		super();
		this.id = generatorId++;
		this.orth = orth;
		this.base = base;
		this.tags = tags;
	}

	public String getOrth() {
		return orth;
	}
	public void setOrth(String orth) {
		this.orth = orth;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getId() {
		 return this.id;
	 }

	@Override
	public String toString() {
		return "Word [Original=" + orth + ", base=" + base + ", tags=" + tags + "]";
	}

	public String parse() {
		if (this.tags.equals("interp") || this.tags.equals("conj")) {
			return this.base;
		}
		return this.tags + ":id" + this.id;
	}
}

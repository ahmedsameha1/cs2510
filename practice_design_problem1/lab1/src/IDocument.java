import tester.Tester;

interface IDocument {
	public ILoStrings generateBibliography();

	public String generateBibliographyString();
}

interface ILoDocuments {
	public ILoStrings generateBibliography();
}

interface ILoStrings {
	ILoStrings add(ILoStrings list);

	ILoStrings insertIfNotThere(String string);

	boolean isThere(String string);

	ILoStrings sort();

	ILoStrings insertSorted(String string);
}

class ConsLoDocuments implements ILoDocuments {
	IDocument first;
	ILoDocuments rest;

	ConsLoDocuments(IDocument first, ILoDocuments rest) {
		this.first = first;
		this.rest = rest;
	}

	public ILoStrings generateBibliography() {
		String firstString = this.first.generateBibliographyString();
		ILoStrings firstBiblio = this.first.generateBibliography();
		ILoStrings restBiblio = this.rest.generateBibliography();
		ILoStrings result;
		if (firstString == null) {
			result = firstBiblio.add(restBiblio);
		} else {
			result = (firstBiblio.insertIfNotThere(firstString)).add(restBiblio);
		}
		return result.sort();
	}
}

class EmptyLoDocuments implements ILoDocuments {
	public ILoStrings generateBibliography() {
		return new EmptyLoStrings();
	}
}

class ConsLoStrings implements ILoStrings {
	String first;
	ILoStrings rest;

	ConsLoStrings(String first, ILoStrings rest) {
		this.first = first;
		this.rest = rest;
	}

	public ILoStrings add(ILoStrings list) {
		return this.rest.add(list).insertIfNotThere(first);
	}

	public ILoStrings insertIfNotThere(String string) {
		if (this.isThere(string)) {
			return this;
		} else {
			return new ConsLoStrings(string, this);
		}
	}

	public boolean isThere(String string) {
		if (string.equals(first)) {
			return true;
		} else {
			return rest.isThere(string);
		}
	}

	public ILoStrings sort() {
		return rest.insertSorted(first);
	}

	public ILoStrings insertSorted(String string) {
		if (first.compareToIgnoreCase(string) < 0) {
			return new ConsLoStrings(first, new ConsLoStrings(string, rest));
		} else {
			return new ConsLoStrings(string, new ConsLoStrings(first, rest));
		}
	}
}

class EmptyLoStrings implements ILoStrings {
	public ILoStrings add(ILoStrings list) {
		return list;
	}

	public ILoStrings insertIfNotThere(String string) {
		return new ConsLoStrings(string, this);
	}

	public boolean isThere(String string) {
		return false;
	}

	public ILoStrings sort() {
		return this;
	}

	public ILoStrings insertSorted(String string) {
		return new ConsLoStrings(string, this);
	}
}

class Book implements IDocument {
	Author author;
	String title;
	ILoDocuments biblio;
	String publisher;

	Book(Author author, String title, ILoDocuments biblo, String publisher) {
		this.author = author;
		this.title = title;
		this.biblio = biblo;
		this.publisher = publisher;
	}

	@Override
	public ILoStrings generateBibliography() {
		return this.biblio.generateBibliography();
	}

	@Override
	public String generateBibliographyString() {
		return this.author.generateNameString() + ". " + "\"" + title + "\"";
	}
}

class WikiArticle implements IDocument {
	Author author;
	String title;
	ILoDocuments biblio;
	String URL;

	WikiArticle(Author author, String title, ILoDocuments biblio, String URL) {
		this.author = author;
		this.title = title;
		this.biblio = biblio;
		this.URL = URL;
	}

	@Override
	public ILoStrings generateBibliography() {
		return this.biblio.generateBibliography();
	}

	@Override
	public String generateBibliographyString() {
		return null;
	}
}

class Author {
	String firstName;
	String lastName;

	Author(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	String generateNameString() {
		return this.lastName + ", " + this.firstName;
	}
}

class ExamplesIDocument {
	ILoDocuments eld = new EmptyLoDocuments();
	ILoStrings els = new EmptyLoStrings();
	IDocument document1 = new Book(new Author("aaa", "bbb"), "book1", eld, "publisher1");
	IDocument document2 = new WikiArticle(new Author("aaa", "bbb"), "wiki1", eld, "url1");
	Author author1 = new Author("jjj", "kkk");
	Author author2 = new Author("lll", "mmm");
	IDocument document3 = new Book(new Author("ccc", "ddd"), "book2", new ConsLoDocuments(document1, eld),
			"publisher1");
	IDocument document4 = new Book(author1, "book3", new ConsLoDocuments(document2, eld), "publisher1");
	IDocument document5 = new WikiArticle(new Author("eee", "fff"), "wiki2", new ConsLoDocuments(document2, eld),
			"url2");
	IDocument document6 = new WikiArticle(new Author("ggg", "hhh"), "wiki3", new ConsLoDocuments(document1, eld),
			"url3");
	IDocument document7 = new Book(author2, "book6", eld, "publisher4");
	IDocument document8 = new Book(author2, "book5",
			new ConsLoDocuments(document3, new ConsLoDocuments(document7, eld)), "publisher3");
	IDocument document9 = new Book(author1, "book4", new ConsLoDocuments(document3, eld), "publisher2");
	IDocument document10 = new Book(author2, "book7",
			new ConsLoDocuments(document3, new ConsLoDocuments(document1, eld)), "publisher2");
	IDocument document11 = new WikiArticle(author1, "wiki4",
			new ConsLoDocuments(document6, new ConsLoDocuments(document3, new ConsLoDocuments(document1, eld))),
			"url4");
	IDocument document12 = new WikiArticle(author1, "wiki4",
			new ConsLoDocuments(document7, new ConsLoDocuments(document5, new ConsLoDocuments(document4, eld))),
			"url4");

	boolean testGenerateBibliography(Tester t) {
		return t.checkExpect(document1.generateBibliography(), els)
				&& t.checkExpect(document2.generateBibliography(), els)
				&& t.checkExpect(document3.generateBibliography(), new ConsLoStrings("bbb, aaa. \"book1\"", els))
				&& t.checkExpect(document4.generateBibliography(), els)
				&& t.checkExpect(document5.generateBibliography(), els)
				&& t.checkExpect(document6.generateBibliography(), new ConsLoStrings("bbb, aaa. \"book1\"", els))
				&& t.checkExpect(document8.generateBibliography(),
						new ConsLoStrings("bbb, aaa. \"book1\"",
								new ConsLoStrings("ddd, ccc. \"book2\"",
										new ConsLoStrings("mmm, lll. \"book6\"", els))))
				&& t.checkExpect(document9.generateBibliography(),
						new ConsLoStrings("bbb, aaa. \"book1\"", new ConsLoStrings("ddd, ccc. \"book2\"", els)))
				&& t.checkExpect(document10.generateBibliography(),
						new ConsLoStrings("bbb, aaa. \"book1\"", new ConsLoStrings("ddd, ccc. \"book2\"", els)))
				&& t.checkExpect(document11.generateBibliography(),
						new ConsLoStrings("bbb, aaa. \"book1\"", new ConsLoStrings("ddd, ccc. \"book2\"", els)))
				&& t.checkExpect(document12.generateBibliography(),
						new ConsLoStrings("kkk, jjj. \"book3\"", new ConsLoStrings("mmm, lll. \"book6\"", els)));
	}
}

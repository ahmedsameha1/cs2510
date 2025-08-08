import tester.Tester;

interface IDocument {
	public ILoStrings generateBibliography();
	public String generateBibliographyString();
}

interface ILoDocuments {
	public ILoStrings generateBibliography();
}

interface ILoStrings {

}

class ConsLoDocuments implements ILoDocuments {
	IDocument first;
	ILoDocuments rest;

	ConsLoDocuments(IDocument first, ILoDocuments rest) {
		this.first = first;
		this.rest = rest;
	}

	public ILoStrings generateBibliography() {
		return new ConsLoStrings(this.first.generateBibliographyString(), this.rest.generateBibliography());
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
}

class EmptyLoStrings implements ILoStrings {
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
	ILoDocuments biblo;
	String URL;

	WikiArticle(Author author, String title, ILoDocuments biblo, String URL) {
		this.author = author;
		this.title = title;
		this.biblo = biblo;
		this.URL = URL;
	}

	@Override
	public ILoStrings generateBibliography() {
		return new EmptyLoStrings();
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
	IDocument document2 = new WikiArticle(new Author("aaa", "bbb"), "book1", eld, "url1");
	IDocument document3 = new Book(new Author("ccc", "ddd"), "book2", new ConsLoDocuments(document1, eld),
			"publisher1");

	boolean testGenerateBibliography(Tester t) {
		return t.checkExpect(document1.generateBibliography(), els)
				&& t.checkExpect(document2.generateBibliography(), els)
				&& t.checkExpect(document3.generateBibliography(), new ConsLoStrings("bbb, aaa. \"book1\"", els));

	}
}

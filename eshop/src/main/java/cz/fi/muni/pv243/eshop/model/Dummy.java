package cz.fi.muni.pv243.eshop.model;

import java.io.Serializable;

public class Dummy implements Serializable {
	private String foo;
	private String bar;

	public Dummy() {
		super();
	}

	/**
	 * @param foo
	 * @param bar
	 */
	public Dummy(String foo, String bar) {
		super();
		this.foo = foo;
		this.bar = bar;
	}

	/**
	 * @return the foo
	 */
	public String getFoo() {
		return foo;
	}

	/**
	 * @param foo
	 *            the foo to set
	 */
	public void setFoo(String foo) {
		this.foo = foo;
	}

	/**
	 * @return the bar
	 */
	public String getBar() {
		return bar;
	}

	/**
	 * @param bar
	 *            the bar to set
	 */
	public void setBar(String bar) {
		this.bar = bar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bar == null) ? 0 : bar.hashCode());
		result = prime * result + ((foo == null) ? 0 : foo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dummy other = (Dummy) obj;
		if (bar == null) {
			if (other.bar != null)
				return false;
		} else if (!bar.equals(other.bar))
			return false;
		if (foo == null) {
			if (other.foo != null)
				return false;
		} else if (!foo.equals(other.foo))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dummy [foo=" + foo + ", bar=" + bar + "]";
	};

}

package com.rcintra.demowebflux.domain;

public final class Cliente {

	private String id;
	private String nome;
	
	public Cliente() {}
	
	public Cliente(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
	@Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}

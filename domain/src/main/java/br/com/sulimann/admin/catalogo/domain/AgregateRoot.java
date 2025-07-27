package br.com.sulimann.admin.catalogo.domain;

public abstract class AgregateRoot<ID extends Identifier> extends Entity<ID> {
  protected AgregateRoot(final ID id) {
    super(id);
  }

}

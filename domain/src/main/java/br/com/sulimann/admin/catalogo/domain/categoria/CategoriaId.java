package br.com.sulimann.admin.catalogo.domain.categoria;

import java.util.Objects;
import java.util.UUID;

import br.com.sulimann.admin.catalogo.domain.Identifier;

public class CategoriaId extends Identifier {
  private final String value;

  private CategoriaId(final String value) {
    this.value = value;
  }

  public static CategoriaId unique(){
    return from(UUID.randomUUID());
  }

  public static CategoriaId from(final String value){
    return new CategoriaId(value);
  }

  public static CategoriaId from(final UUID value){
    return new CategoriaId(value.toString().toLowerCase());
  }

  public String getValue(){
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    CategoriaId that = (CategoriaId) o;
    return Objects.equals(getValue(), that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getValue());
  }
}

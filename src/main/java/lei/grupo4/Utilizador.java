package lei.grupo4;

public class Utilizador {

    private int id;
    private String nome;
    private String username;
    private String password;
    private Perfil perfil;
    private boolean ativo;

    public Utilizador(
            int id,
            String nome,
            String username,
            String password,
            Perfil perfil,
            boolean ativo
    ) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.perfil = perfil;
        this.ativo = ativo;
    }

    public boolean autenticar(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean temPermissao(Perfil perfilNecessario) {
        return this.perfil == perfilNecessario;
    }

    public String getNome() {
        return nome;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public boolean isAtivo() {
        return ativo;
    }
}

package clinica_medica_api.medico;

import clinica_medica_api.endereco.DadosEndereco;
import clinica_medica_api.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaCadastroMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}

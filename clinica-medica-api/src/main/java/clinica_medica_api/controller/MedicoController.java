package clinica_medica_api.controller;

import clinica_medica_api.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public List<DadosListagemMedico> listar(){
        return repository.findAllByAtivoTrue().stream().map(DadosListagemMedico::new).toList();
    }


    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaCadastroMedico dados){
        Medico medico = repository.findById(dados.id())
                        .orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
        medico.atualizarInformacoes(dados);
    }

    //Com exclusão lógica
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        Medico medico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
        medico.excluir();
    }
}

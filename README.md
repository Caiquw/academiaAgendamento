# Agendamento Academia

## Objetivo

Aplicativo para marcar presença em treinos de uma academia. O aluno vê a lista de treinos e, no detalhe de cada um, pode confirmar presença, cancelar ou entrar na lista de espera. Os dados são simulados (mocks).

## Como rodar

1. Abra a pasta do projeto no Android Studio (versão recente, compatível com o Android Gradle Plugin 9.4.1).
2. Aguarde o Gradle Sync terminar (precisa de internet na primeira vez).
3. Use um emulador ou aparelho com Android 13 (API 33) ou superior.
4. Clique em Run.

Não há chaves de API nem arquivo .env.

## Bibliotecas externas

- **AndroidX Core KTX:** extensões Kotlin para APIs do Android.
- **AndroidX AppCompat:** base das Activities e da barra superior.
- **Material Components:** tema visual.
- **AndroidX RecyclerView:** lista de treinos.
- **AndroidX Activity KTX:** registerForActivityResult, para a tela de detalhe devolver o resultado à lista.

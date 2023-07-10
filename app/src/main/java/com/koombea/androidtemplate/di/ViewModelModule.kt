import com.koombea.presenter.ui.character.CharacterListViewModel
import com.koombea.presenter.ui.onboarding.OnboardingViewModel
import com.koombea.presenter.ui.settings.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { CharacterListViewModel(getCharactersUseCase = get()) }
    viewModel { ProfileViewModel(getUserCase = get()) }
    viewModel { OnboardingViewModel(get()) }
}

package com.hellopharmacy.utilities.daggerinjections.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hellopharmacy.utilities.common.ViewModelFactory
import com.hellopharmacy.utilities.daggerinjections.annotations.ViewModelKey
import com.hellopharmacy.viewmodels.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Provides map of all ViewModels and a ViewModelFactory for dependencies
 *
 * Created on ***  on 02/01/2021.
 */
@Module
abstract class ViewModelModule
{

    @Binds
    @IntoMap
    @ViewModelKey(SignupViewModel::class)
    abstract fun bindSignupViewModel(viewModel: SignupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SigninViewModel::class)
    abstract fun bindSigninViewModel(viewModel: SigninViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AdsViewModel::class)
    abstract fun bindAdsViewModel(viewModel: AdsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UploadPrescriptionViewModel::class)
    abstract fun uploadPrescriptionViewModel(viewModel: UploadPrescriptionViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(CheckPrescriptionViewModel::class)
    abstract fun CheckPrescriptionViewModel(viewModel: CheckPrescriptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BeautyCareStoresViewModel::class)
    abstract fun BeautyCareStoresViewModel(viewModel: BeautyCareStoresViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel::class)
    abstract fun ForgotPasswordViewModel(viewModel: ForgotPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResetPasswordViewModel::class)
    abstract fun ResetPasswordViewModel(viewModel: ResetPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AvailableStoresViewModel::class)
    abstract fun AvailableStoresViewModel(viewModel: AvailableStoresViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AvailableStoreDetailsViewModel::class)
    abstract fun AvailableStoreDetailsViewModel(viewModel: AvailableStoreDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CouponsViewModel::class)
    abstract fun CouponsViewModel(viewModel: CouponsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchProductsViewModel::class)
    abstract fun SearchProductsViewModel(viewModel: SearchProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel::class)
    abstract fun ProductDetailsViewModel(viewModel: ProductDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyCartViewModel::class)
    abstract fun MyCartViewModel(viewModel: MyCartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShippingAddressViewModel::class)
    abstract fun GetAddressesViewModel(viewModel: ShippingAddressViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddEditAddressViewModel::class)
    abstract fun AddEditAddressViewModel(viewModel: AddEditAddressViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlaceOrderViewModel::class)
    abstract fun PlaceOrderViewModel(viewModel: PlaceOrderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlacePrescriptionOrderViewModel::class)
    abstract fun PlacePrescriptionOrderViewModel(viewModel: PlacePrescriptionOrderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddAppointmentRatingViewModel::class)
    abstract fun AddAppointmentRatingViewModel(viewModel: AddAppointmentRatingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppointmentRatingsViewModel::class)
    abstract fun AppointmentRatingsViewModel(viewModel: AppointmentRatingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductCategoriesViewModel::class)
    abstract fun ProductCategoriesViewModel(viewModel: ProductCategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductSubCategoriesViewModel::class)
    abstract fun ProductSubCategoriesViewModel(viewModel: ProductSubCategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlaceViewModel::class)
    abstract fun PlaceViewModel(viewModel: PlaceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppointmentViewModel::class)
    abstract fun AppointmentViewModel(viewModel: AppointmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookAppointmentViewModel::class)
    abstract fun BookAppointmentViewModel(viewModel: BookAppointmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrdersViewModel::class)
    abstract fun OrdersViewModel(viewModel: OrdersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun ProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    abstract fun ChangePasswordViewModel(viewModel: ChangePasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactSupportViewModel::class)
    abstract fun ContactSupportViewModel(viewModel: ContactSupportViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MembershipViewModel::class)
    abstract fun MembershipViewModel(viewModel: MembershipViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoyaltyPointsViewModel::class)
    abstract fun LoyaltyPointsViewModel(viewModel: LoyaltyPointsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecuritySettingsViewModel::class)
    abstract fun SecuritySettingsViewModel(viewModel: SecuritySettingsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}
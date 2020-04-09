package com.haroldocosta.bankapp.loginScreen

import com.haroldocosta.bankapp.loginScreen.UserAccount
import com.haroldocosta.bankapp.utils.extensions.ResponseError
import com.haroldocosta.bankapp.utils.validations.Validations
import io.reactivex.Single
import javax.inject.Inject

//   REGRA DE NEGOCIO

class LoginInteractor @Inject constructor(private val loginRepository: LoginRepository) {

        fun login(user: String, password: String): Single<UserAccount> {
            return when {
                user.isBlank() -> Single.error(LoginBusiness.UserInvalid)
                !Validations.isCpfValid(user) && !Validations.isEmailValid(user) -> Single.error(
                    LoginBusiness.UserInvalid
                )
                password.isBlank() -> Single.error(LoginBusiness.PasswordInvalid)
                !Validations.hasSpecialCharacter(password) -> Single.error(LoginBusiness.PasswordNeedSpecialCharacter)
                !Validations.hasCapitalizedLetter(password) -> Single.error(LoginBusiness.PasswordNeedCapitalizedLetter)
                !Validations.hasNumber(password) -> Single.error(LoginBusiness.PasswordNeedNumber)
                else -> loginRepository.login(user, password)
            }
        }

}


sealed class LoginBusiness {
    object UserInvalid : BusinessException()
    object PasswordInvalid : BusinessException()
    object PasswordNeedSpecialCharacter : BusinessException()
    object PasswordNeedCapitalizedLetter : BusinessException()
    object PasswordNeedNumber : BusinessException()
}


/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific should extend [BusinessException] class.
 */
sealed class AppException(message: String = "") : RuntimeException(message)

/**
 * Object used to identify a generic http request exception
 */
object HttpError : AppException("Server http response code is not 200, 400 or 403")

/**
 * Object used to identify a network without internet connection
 */
object NetworkError : AppException()

/**
 * Object used to identify an user Unauthorized
 */
object UnauthorizedError : AppException()

/**
 * Unknown error
 * */
object UnknownException : AppException("Need check Logcat to see more information")

/**
 * Exception used when the server returns business validations
 */
class ServerError(val error: ResponseError) : AppException()

/**
 * Exception used when server returns authentication with login or password invalid
 * */
object InvalidCredentials : AppException()

/**
 * Extend this class for feature specific failures.
 *
 * */
abstract class BusinessException : AppException()

/**
 * Exception thrown when an essential parameter is missing in the
 * backend/network response.
 *
 */
class EssentialParamMissingException(
    missingParam: String,
    rawObject: Any
) : AppException("The params: $missingParam are missing in received object: $rawObject")

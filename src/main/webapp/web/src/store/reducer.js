import axios from "axios";

const initialState = {
    authOk: false,
}


const reducer = (state = initialState, action) => {
    switch (action.type) {
        case 'VERIFY_AUTH':
            console.log("verify auth")
            console.log(state.authOk)
            return {
                ...state,
                authOk:  action.authOk
            }

        case 'LOGOUT':
            console.log("Dispatch logout")
            return {
                ...state,
                authOk: false
            }
    }
    return state;
}

export default reducer;


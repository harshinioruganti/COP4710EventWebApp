const jwt = require("jsonwebtoken");
require("dotenv").config();

exports.createToken = function (id, res) {
    const token = _createToken(id);
    res.cookie('jwt', token.accessToken, { httpOnly: true, path: '/' });
    return true;
  }

_createToken = function ( id )
{
    try
    {
      const expiration = new Date();
      const user = {id:id};
      secret = process.env.ACCESS_TOKEN_SECRET
      const accessToken =  jwt.sign( user, secret);
      // In order to expire with a value other than the default, use the 
       // following
      /*
      const accessToken= jwt.sign(user,process.env.ACCESS_TOKEN_SECRET, 
         { expiresIn: '30m'} );
                       '24h'
                      '365d'
      */
      var ret = {accessToken:accessToken,id:id};
    }

    catch(e)
    {
      var ret = {error:e.message};
    }

    return ret;
}

exports.isExpired = function( token )
{
   var isError = jwt.verify( token, process.env.ACCESS_TOKEN_SECRET, 
     (err, verifiedJwt) =>
   {
     if( err )
     {
       return true;
     }
     else
     {
       return false;
     }
   });
   return isError;
}
exports.refresh = function( token )
{
  let ud = jwt.decode(token,{complete:true});
  let id = ud.payload.id;
  return _createToken(id );
}

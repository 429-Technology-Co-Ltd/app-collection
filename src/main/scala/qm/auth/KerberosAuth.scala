package qm.auth

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.security.UserGroupInformation

object KerberosAuth {
    def auth(debug: Boolean): Unit = {
      try {
        System.setProperty("java.security.krb5.conf", "/etc/krb5.conf")
        System.setProperty("javax.security.auth.useSubjectCredsOnly", "false")
        if (debug) {
          System.setProperty("sun.security.krb5.debug", "true")
        }
        val conf: Configuration = new Configuration()

        conf.set("hadoop.security.authentication", "Kerberos")
        UserGroupInformation.setConfiguration(conf)
        UserGroupInformation.loginUserFromKeytab("hive@QIAOMENG.COM","/opt/software/kerberos/hive.keytab")
      }catch {
        case e:Exception=>e.printStackTrace()
      }
    }
  }



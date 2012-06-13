package cz.fi.muni.pv243.eshop.controller;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

import cz.fi.muni.pv243.eshop.model.Customer;
import cz.fi.muni.pv243.eshop.service.Admin;
import cz.fi.muni.pv243.eshop.service.BasicPermission;
import cz.fi.muni.pv243.eshop.service.Seller;

public class Authorization
{
   @Secures
   @Admin
   public boolean isAdmin(Identity identity) {
      if (!identity.isLoggedIn()) {
         return false;
      }
      
      return "admin".equals(((Customer)identity.getUser()).getRole());
   }
   
   @Secures
   @Seller
   public boolean isSeller(Identity identity) {
      if (!identity.isLoggedIn()) {
         return false;
      }
      
      return "seller".equals(((Customer)identity.getUser()).getRole());
   }
   
   @Secures
   @BasicPermission
   public boolean isCustomer(Identity identity) {
      if (!identity.isLoggedIn()) {
         return false;
      }
      
      return "customer".equals(((Customer)identity.getUser()).getRole());
   }
}

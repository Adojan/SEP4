﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace ReadingAccess
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class ForProjectEntities : DbContext
    {
        public ForProjectEntities()
            : base("name=ForProjectEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<AllCO2Facts> AllCO2Facts { get; set; }
        public virtual DbSet<AllTemperatureFact> AllTemperatureFacts { get; set; }
        public virtual DbSet<AllHumidityFact> AllHumidityFacts { get; set; }
        public virtual DbSet<Room> Rooms { get; set; }
        public virtual DbSet<STAGE_AllTemperatureFacts> STAGE_AllTemperatureFacts { get; set; }
        public virtual DbSet<User> Users { get; set; }
        public virtual DbSet<STAGE_AllCO2Facts> STAGE_AllCO2Facts { get; set; }
        public virtual DbSet<STAGE_AllHumidityFacts> STAGE_AllHumidityFacts { get; set; }
    }
}
